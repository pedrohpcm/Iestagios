import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { RegisterPage } from '../register/register';
import { TabPage } from '../tab/tab';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';

@Component({
	selector: 'page-enter',
	templateUrl: 'enter.html'
})
export class EnterPage {
	public email: string;
	public password: string;

	constructor(public navCtrl: NavController, public storage: Storage, public toastCtrl: ToastController) {
		
	}

	ToRegisterPage() {
		this.navCtrl.push(RegisterPage);
	}

	ToEnter() {
		// Armazenar Localmente.
		this.storage.set('email', this.email);
    	this.storage.set('password', this.password);

		// Objetos necessários.
		var Profile = {
			email: this.email,
			password: this.password
		}

		let navigation = this.navCtrl;
		let toast = this.toastCtrl.create({
			message: 'Você não é cadastrado ainda!',
			duration: 5000, 
			position: 'top',
			showCloseButton: true,
			closeButtonText: 'Entendi'
		});

		// Abrir conexão.
		var host = "ws://localhost:8080/iEstagiosAPI/server";
		var socket = new WebSocket(host);

		socket.onopen = function() {
			socket.send("enter" + "," + Profile.email + "," + Profile.password);
		}

		socket.onmessage = function(text) {
			if (text.data === 'yes') {
				navigation.push(TabPage);	
			}
			else {
				toast.present();
			}
		}
	}
}