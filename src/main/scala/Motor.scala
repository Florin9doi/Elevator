import akka.actor.{Props, Actor, ActorLogging}
import scala.concurrent.duration._

object Motor {
	case object Urca
	case object Coboara
	case object Opreste
	case object Tick
}

class Motor extends Actor with ActorLogging {
	import context.dispatcher
	val schedule = context.system.scheduler.schedule(0 milliseconds, 100 milliseconds, self, Motor.Tick)
	var status = 0

	def receive = {
		case Motor.Coboara =>
			status = 1
		case Motor.Urca =>
			status = 2
   		case Motor.Opreste =>   		
			status = 0
   		case Motor.Tick =>
   			if(status == 1)
     			print("-")
   			if(status == 2)
     			print("+")
	}

	override def postStop(): Unit = schedule.cancel
}