import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Globals } from '../common/global-variables';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy {
  empDetails: any;
  managerDetails: any;
  constructor(
    private global: Globals,
    private router: Router
  ) {
    this.empDetails = JSON.parse(sessionStorage.getItem("employeeDetails"));
    this.managerDetails = JSON.parse(sessionStorage.getItem("managerDetails"));

  }

  ngOnInit(): void {

  }

  ngOnDestroy(): void {

  }


}
