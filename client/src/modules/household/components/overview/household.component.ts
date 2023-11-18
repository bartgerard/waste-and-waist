/* eslint-disable prettier/prettier */

import { Component, OnInit } from '@angular/core'
import { PrimeNGConfig } from 'primeng/api'

interface Appliance {
  type: string;
}

@Component({
  selector: 'app-household',
  templateUrl: './household.component.html',
  styleUrls: ['./household.component.scss'],
})
export class HouseholdComponent implements OnInit {

  title = 'client'
  appliances: Appliance[];
  selectedAppliance!: Appliance;

  constructor(private primengConfig: PrimeNGConfig) {
    this.appliances = [
      { type: 'Microwave' },
      { type: 'Coffee machine' },
      { type: 'Oven' },
      { type: 'Fridge' },
      { type: 'Frier' },
    ]
  }
  ngOnInit(): void {
    console.log("init")
  }

  add() {
    console.log("added")
  }

}