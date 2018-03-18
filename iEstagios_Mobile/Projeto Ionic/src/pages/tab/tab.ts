import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { ProfilePage } from '../profile/profile';
import { StagePage } from '../stage/stage';

@Component({
  selector: 'page-tab',
  templateUrl: 'tab.html'
})
export class TabPage {
  tab1: any;
  tab3: any;

  constructor(public navCtrl: NavController) {
  	this.tab1 = ProfilePage;
  	this.tab3 = StagePage; 
  }

}
