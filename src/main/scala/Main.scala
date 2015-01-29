import akka.actor.{Props, Actor, ActorRef, ActorSystem}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import java.util.concurrent.TimeUnit;

object Main extends App {
	val system = ActorSystem("Lift")
	val buton = system.actorOf(Props[Buton], "buton")

	system.scheduler.scheduleOnce(Duration.create(50, TimeUnit.MILLISECONDS)) {
		buton ! Buton.Parter
	}
	
	system.scheduler.scheduleOnce(Duration.create(200, TimeUnit.MILLISECONDS)) {
		buton ! Buton.Etaj1
	}
	
	system.scheduler.scheduleOnce(Duration.create(400, TimeUnit.MILLISECONDS)) {
		buton ! Buton.Etaj3
	}
	
	system.scheduler.scheduleOnce(Duration.create(1300, TimeUnit.MILLISECONDS)) {
		buton ! Buton.Etaj2
	}

	system.scheduler.scheduleOnce(Duration.create(3000, TimeUnit.MILLISECONDS)) {
		buton ! Buton.Etaj2
	}
	
	system.scheduler.scheduleOnce(Duration.create(5000, TimeUnit.MILLISECONDS)) {
		system.shutdown()
	}
}