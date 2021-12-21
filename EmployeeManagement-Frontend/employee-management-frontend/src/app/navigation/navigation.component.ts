import { Component, OnInit } from '@angular/core';
import { Router, RouterEvent } from '@angular/router';
import { filter } from 'rxjs';
import { Globals } from '../common/global-variables';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {
  empDetails: any;
  constructor(
    private global: Globals,
    private router: Router
  ) {
    if (sessionStorage.getItem("employeeId") === null) {
      this.router.navigate(['/'])
    }
    this.empDetails = JSON.parse(sessionStorage.getItem("employeeDetails"));
  }
  ngOnInit(): void {
  }
}
