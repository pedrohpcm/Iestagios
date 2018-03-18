import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';

@Component({
  selector: 'page-profile',
  templateUrl: 'profile.html'
})
export class ProfilePage {
  public firstname: string;
  public lastname: string;
  public email: string;
  public password: string;
  public age: number;

  constructor(public navCtrl: NavController, public storage: Storage, public toastCtrl: ToastController) {

  }

  ToUpdateProfile() {
    // Armazenar Localmente.
  	this.storage.set('firstname', this.firstname);
  	this.storage.set('lastname', this.lastname);
  	this.storage.set('email', this.email);
  	this.storage.set('age', this.age);

    this.storage.get('password').then((val) => {
      this.password = val;
    });

  	this.storage.get('firstname').then((val) => {
  		let toast = this.toastCtrl.create({
  			message: val + ', seus dados foram armazenados com sucesso!',
  			duration: 5000,
  			position: 'top',
  			showCloseButton: true,
  			closeButtonText: 'Entendi'
  		});
  		toast.present();
  	});

    // Armazenar no Banco de Dados.
  	var Profile = {
  		firstname: this.firstname,
  		lastname: this.lastname,
  		email: this.email,
      password: this.password,
  		age: this.age
  	}

    var host = "ws://localhost:8080/iEstagiosAPI/server";
    var socket = new WebSocket(host);

    socket.onopen = function() {
      socket.send("profile" + "," + Profile.firstname + "," + Profile.lastname + "," + Profile.email + "," + Profile.password + "," + Profile.age);
    };
  }

}
