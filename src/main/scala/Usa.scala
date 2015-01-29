import akka.actor.{Props, Actor, ActorLogging}

object Usa {
	case class Deschide(etaj: Int)
	case class Inchide(etaj: Int)
}

class Usa extends Actor with ActorLogging {
	var denumiri = Array("Parter", "Etaj1", "Etaj2", "Etaj3", "Etaj4", "Etaj5")

	def receive = {
		case Usa.Deschide(etaj) =>
			log.info("Se deschid ușile la " + denumiri(etaj))
		case Usa.Inchide(etaj) =>
			log.info("Se închid ușile la " + denumiri(etaj))
	}
}