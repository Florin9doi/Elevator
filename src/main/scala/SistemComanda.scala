import akka.actor.{Props, Actor, ActorLogging}

object SistemComanda {
	case class Comanda(etajDorit: Int)
}

class SistemComanda extends Actor with ActorLogging {
	val motor = context.actorOf(Props[Motor], "motor")
	val usa = context.actorOf(Props[Usa], "usa")
	var denumiri = Array("Parter", "Etaj1", "Etaj2", "Etaj3", "Etaj4", "Etaj5")
	var etajCurent = 0

	def receive = {
		case SistemComanda.Comanda(etajDorit) =>
			if(etajDorit == etajCurent)
				log.info("Comandă invalidă - Liftul se află deja la "+ denumiri(etajDorit))
			if(etajDorit < etajCurent) {
				usa ! Usa.Inchide(etajCurent)
				motor ! Motor.Coboara
				Thread.sleep(500 * (etajCurent - etajDorit))
				motor ! Motor.Opreste
				usa ! Usa.Deschide(etajDorit)
				etajCurent = etajDorit
			}
			if(etajDorit > etajCurent) {
				usa ! Usa.Inchide(etajCurent)
				motor ! Motor.Urca
				Thread.sleep(500 * (etajDorit - etajCurent))
				motor ! Motor.Opreste
				usa ! Usa.Deschide(etajDorit)
				etajCurent = etajDorit
			}
	}
}