import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { Storage } from '@ionic/storage';

@Component({
	selector: 'page-register',
	templateUrl: 'register.html'
})
export class RegisterPage {
	public email: string;
	public password: string;

	constructor(public navCtrl: NavController, public storage: Storage) {

	}

	ToEnterPage() {
		this.navCtrl.pop();
	}

	ToRegister() {
		// Armazenar Localmente.
		this.storage.set('email', this.email);
    	this.storage.set('password', this.password);

    	// Armazenar no Banco de Dados.
		var Profile = {
			email: this.email,
			password: this.password
		}

		var host = "ws://localhost:8080/iEstagiosAPI/server";
		var socket = new WebSocket(host);

		socket.onopen = function() {
			socket.send("register" + "," + Profile.email + "," + Profile.password);
		}
	}
}