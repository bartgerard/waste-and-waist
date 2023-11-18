/* eslint-disable prettier/prettier */

import { Component, OnInit } from '@angular/core'
import { PrimeNGConfig } from 'primeng/api'

interface Location {
  locationId: number;
  name: string;
}

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
  locations: Location[];
  selectedAppliance!: Appliance;
selectedLocation!: Location;

  constructor(private primengConfig: PrimeNGConfig) {
    this.locations = [
      { locationId: 1, name: 'Villa in Turkey' },
      { locationId: 2, name: 'House in Wallonia' },
      { locationId: 3, name: 'Apartment in New York' },
      { locationId: 4, name: 'Cottage in Brazil' },
      { locationId: 5, name: ' Mansion in Abu Dhabi' },
    ]
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