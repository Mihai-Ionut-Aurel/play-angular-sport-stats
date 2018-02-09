import { Component } from '@angular/core';
import { AppService } from './app.service';
import {until} from "selenium-webdriver";
import titleContains = until.titleContains;

@Component({
  moduleId: module.id,
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string;

  constructor(private appService: AppService) {
    this.title= "Gracenote Job Demo"
  }
}
