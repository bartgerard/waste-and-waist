/* eslint-disable prettier/prettier */

import { Component, OnInit } from '@angular/core'
import { PrimeNGConfig } from 'primeng/api'

@Component({
  selector: 'app-root',
  templateUrl: './household.component.html',
  styleUrls: ['./household.component.scss'],
})
export class HouseholdComponent implements OnInit {
  title = 'client'

  constructor(private primengConfig: PrimeNGConfig) { }
  ngOnInit(): void {
    console.log("init")
  }

}