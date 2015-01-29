import akka.actor.{Props, Actor, ActorLogging}

object Buton {
	case object Parter
	case object Etaj1
	case object Etaj2
	case object Etaj3
	case object Etaj4
	case object Etaj5
}

class Buton extends Actor with ActorLogging {
	val comanda = context.actorOf(Props[SistemComanda], "comanda")

	def receive = {
		case Buton.Parter =>
			log.info("Parter")
			comanda ! SistemComanda.Comanda(0)
		case Buton.Etaj1 =>
			log.info("Etaj1")
			comanda ! SistemComanda.Comanda(1)
		case Buton.Etaj2 =>
			log.info("Etaj2")
			comanda ! SistemComanda.Comanda(2)
		case Buton.Etaj3 =>
			log.info("Etaj3")
			comanda ! SistemComanda.Comanda(3)
		case Buton.Etaj4 =>
			log.info("Etaj4")
			comanda ! SistemComanda.Comanda(4)
		case Buton.Etaj5 =>
			log.info("Etaj5")
			comanda ! SistemComanda.Comanda(5)
	}
}
