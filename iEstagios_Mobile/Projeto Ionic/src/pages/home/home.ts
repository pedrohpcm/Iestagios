import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { ViewChild } from '@angular/core';
import { Slides } from 'ionic-angular';
//import { TabPage } from '../tab/tab';
import { EnterPage } from '../enter/enter';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  @ViewChild(Slides) slides: Slides;

  constructor(public navCtrl: NavController) {

  }

  ToNextSlide() {
  	this.slides.slideNext(500);
  }

  ToPrevSlide() {
  	this.slides.slidePrev(500);
  }

  ToTabPage() {
  	this.navCtrl.push(EnterPage);
  }

}
