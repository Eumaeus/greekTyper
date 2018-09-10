package betaTyper 

import com.thoughtworks.binding.{Binding, dom}
import com.thoughtworks.binding.Binding.{BindingSeq, Var, Vars}
import scala.scalajs.js
import scala.scalajs.js._
import org.scalajs.dom._
import org.scalajs.dom.ext._
import scala.scalajs.js.Dynamic.{ global => g }
import org.scalajs.dom.raw._
import org.scalajs.dom.document
import org.scalajs.dom.raw.Event
import org.scalajs.dom.ext.Ajax

import edu.holycross.shot.greek._

import scala.scalajs.js.annotation.JSExport
import js.annotation._


@JSExportTopLevel("betaTyper.MainView")
object MainView {

	//val textView = O2View.o2div

	@dom
	def typingDiv = {
		val greekKeyUpHandler = { event: KeyboardEvent =>
			(event.currentTarget, event.keyCode) match {
				case(input: HTMLTextAreaElement, _) =>  {
					js.Dynamic.global.document.getElementById("greekOutput").text = MainController.greekify(input.value.toString)
				}
				case _ =>
			}
		}

		<p>Type Beta Code Here:</p>
		<textarea
		class={ s"greekInputField" }
		id="greekInput"
		cols={ 50 }
		rows={ 3 }
		value=""
		onkeyup={ greekKeyUpHandler }>
		</textarea>	

		<p id="uCodeLabel">Unicode (click to copy):</p>
		<p class="bigPosTag">
		<textarea id="hiddenTextArea"></textarea>
		<a id="greekOutput"
			  onclick={ event: Event => {
				val thisTarget = event.target.asInstanceOf[org.scalajs.dom.raw.HTMLAnchorElement]
				val selectedText:String = thisTarget.text
				g.console.log(selectedText)
			    val hiddenTextArea = js.Dynamic.global.document.getElementById("hiddenTextArea")
			    hiddenTextArea.textContent = selectedText.trim
			    hiddenTextArea.select()
			    val successful = document.execCommand("copy")
				successful match {
					case true => {
						val message:String = s"""Copied "${selectedText}" to clipboard."""
						MainController.updateUserMessage(message, 0)
					}
					case false => {
						val message:String = s"""Failed to copy "${selectedText}" to clipboard."""
						MainController.updateUserMessage(message, 2)
					}
				}
			}}	></a>
			</p>

	}


	@dom
	def mainMessageDiv = {
			<div id="main_message" class={ s"app_message ${MainModel.userMessageVisibility.bind} ${MainModel.userAlert.bind}" } >
				<p> { MainModel.userMessage.bind }  </p>
			</div>
	}

	@dom
	def mainDiv = {
		<div id="main-wrapper">
		<header>
			Greek Typer
			<span id="app_header_versionInfo">version { BuildInfo.version }</span>
		</header>

		<article id="main_Container">
			{ guideDiv.bind }

			{ mainMessageDiv.bind }
			{ typingDiv.bind }

		</article>
		 <div class="push"></div>
		<footer>
		{ footer.bind }
		</footer>
	</div>
	}

	@dom
	def guideDiv = {
		<div id="guideDiv">
			<h2>Beta Code Guide</h2>
			<table id="betaGuide">
			<thead>
			<tr>
			<th> α </th>
			<th> β </th>
			<th> γ </th>
			<th> δ </th>
			<th> ε </th>
			<th> ζ </th>
			<th> η </th>
			<th> θ </th>
			<th> ι </th>
			<th> κ </th>
			<th> λ </th>
			<th> μ </th>
			<th> ν </th>
			<th> ξ </th>
			<th> ο </th>
			<th> π </th>
			<th> ρ </th>
			<th> σ </th>
			<th> ς </th>
			<th> τ </th>
			<th> υ </th>
			<th> φ </th>
			<th> χ </th>
			<th> ψ </th>
			<th> ω </th>
			<th> ἀ </th>
			<th> ἁ </th>
			<th> ά </th>
			<th> ὰ </th>
			<th> ᾶ </th>
			<th> ᾳ </th>
			<th> ᾄ </th>
			<th> ϊ </th>
			<th> Α </th>
			<th> Β </th>
			<th> Γ </th>
			<th> Ἀ </th>
			<th> Ἄ </th>
			<th> . </th>
			<th> ; </th>
			<th> : </th>
			<th> &lsquo; </th>
			</tr>
			</thead>
			<tbody>
			<tr>
			<td> <code>a</code> </td>
			<td> <code>b</code> </td>
			<td> <code>g</code> </td>
			<td> <code>d</code> </td>
			<td> <code>e</code> </td>
			<td> <code>z</code> </td>
			<td> <code>h</code> </td>
			<td> <code>q</code> </td>
			<td> <code>i</code> </td>
			<td> <code>k</code> </td>
			<td> <code>l</code> </td>
			<td> <code>m</code> </td>
			<td> <code>n</code> </td>
			<td> <code>c</code> </td>
			<td> <code>o</code> </td>
			<td> <code>p</code> </td>
			<td> <code>r</code> </td>
			<td> <code>s</code> </td>
			<td> <code>s</code> </td>
			<td> <code>t</code> </td>
			<td> <code>u</code> </td>
			<td> <code>f</code> </td>
			<td> <code>x</code> </td>
			<td> <code>y</code> </td>
			<td> <code>w</code> </td>
			<td> <code>a)</code> </td>
			<td> <code>a(</code> </td>
			<td> <code>a/</code> </td>
			<td> <code>a\</code> </td>
			<td> <code>a=</code> </td>
			<td> <code>a|</code> </td>
			<td> <code>a)/|</code> </td>
			<td> <code>i+</code> </td>
			<td> <code>*a</code> </td>
			<td> <code>*b</code> </td>
			<td> <code>*g</code> </td>
			<td> <code>*a)</code> </td>
			<td> <code>*a)/</code> </td>
			<td> <code>.</code> </td>
			<td> <code>;</code> </td>
			<td> <code>:</code> </td>
			<td> <code>'</code> </td>
			</tr>
			</tbody>
			</table>
		</div>
	}

	@dom
	def footer = {
		<p>
		GreekTyper was written in 2018 by Christopher Blackwell with <a href="https://github.com/neelsmith/greek">code written by Neel Smith</a>. It is licensed under the GPL. Source and issue-tracker at <a href="https://github.com/Eumaeus/greekTyper">https://github.com/Eumaeus/greekTyper</a>. 
		</p>
	}
}
