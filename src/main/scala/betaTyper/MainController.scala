package betaTyper 
import com.thoughtworks.binding.{Binding, dom}
import com.thoughtworks.binding.Binding.{BindingSeq, Var, Vars}
import scala.scalajs.js
import scala.scalajs.js._
import js.annotation._
import scala.concurrent._
//import ExecutionContext.Implicits.global
import collection.mutable
import collection.mutable._
import scala.scalajs.js.Dynamic.{ global => g }
import org.scalajs.dom._
import org.scalajs.dom.ext._
import org.scalajs.dom.raw._

import edu.holycross.shot.greek._

import monix.execution.Scheduler.Implicits.global
import monix.eval._

import scala.scalajs.js.annotation.JSExport

@JSExportTopLevel("betaTyper.MainController")
object MainController {


	/* 
		Initiate app with a URL to an online CEX file	
	*/
	@JSExport
	def main: Unit = {
		dom.render(document.body, MainView.mainDiv)
	}

	/*
	 	Handles displaying messages to the user, color-coded according to type.
	 	Fades after 10 seconds.		
	*/
	def updateUserMessage(msg: String, alert: Int): Unit = {
		MainModel.userMessageVisibility.value = "app_visible"
		MainModel.userMessage.value = msg
		alert match {
			case 0 => MainModel.userAlert.value = "default"
			case 1 => MainModel.userAlert.value = "wait"
			case 2 => MainModel.userAlert.value = "warn"
		}
		js.timers.clearTimeout(MainModel.msgTimer)
		MainModel.msgTimer = js.timers.setTimeout(10000){ MainModel.userMessageVisibility.value = "app_hidden" }
	}
	def greekify(s:String):String = {
		if (s.size > 0){
			val gs:LiteraryGreekString = LiteraryGreekString(s)
			val ugs:String = ucodePlus(gs)
			ugs
		} else {
			""
		}
	}

	def ucodePlus(s:LiteraryGreekString):String = {
		val sigmaTerminators:Vector[String] = Vector(",",".",":", ";", "'", "—", " ", "\t")
		val punctuationMatcher = "[.,:;]".r
		val uc1:String = s.ucode.replaceAll(":","·")
		val uc2:String = {
			if (uc1.last == 'σ') {
				s"ς${uc1.reverse.tail}".reverse
			} else { uc1 }
		}
		val matcher = "σ.".r	
		val uc3 = {
			matcher.replaceAllIn(uc2, m => {
				val secondChar:String = m.group(0).tail
				if (sigmaTerminators.contains(secondChar)) { s"ς${secondChar}"}
				else { s"σ${secondChar}"}
			})
		}

		uc3

	}


}
