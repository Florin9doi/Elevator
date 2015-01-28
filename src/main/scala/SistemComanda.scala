import akka.actor.{Props, Actor, ActorLogging}

object SistemComanda {
	case class Comanda(etajDorit: Int)
}

class SistemComanda extends Actor with ActorLogging {
	val motor = context.actorOf(Props[Motor], "motor")
	var etajCurent = 0
	var denumiri = Array("Parter", "Etaj1", "Etaj2", "Etaj3", "Etaj4", "Etaj5")

	def receive = {
		case SistemComanda.Comanda(etajDorit) =>
			if(etajDorit == etajCurent)
				log.info("Comandă invalidă")
			if(etajDorit < etajCurent) {
				log.info("Se închid ușile la etajul " + denumiri(etajCurent))
				motor ! Motor.Coboara
				Thread.sleep(500 * (etajCurent - etajDorit))
				motor ! Motor.Opreste
				log.info("Se deschid ușile la etajul " + denumiri(etajDorit))
				etajCurent = etajDorit
			}
			if(etajDorit > etajCurent) {
				log.info("Se închid ușile la etajul " + denumiri(etajCurent))
				motor ! Motor.Urca
				Thread.sleep(500 * (etajDorit - etajCurent))
				motor ! Motor.Opreste
				log.info("Se deschid ușile la etajul " + denumiri(etajDorit))
				etajCurent = etajDorit
			}
	}
}