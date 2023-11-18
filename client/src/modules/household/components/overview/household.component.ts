import { Component, OnInit } from '@angular/core'
import { PrimeNGConfig } from 'primeng/api'
import { Location } from './Location'
import { Appliance } from './Appliance'

@Component({
  selector: 'app-household',
  templateUrl: './household.component.html',
  styleUrls: ['./household.component.scss'],
})
export class HouseholdComponent implements OnInit {
  title = 'client'
  appliances: Appliance[]
  locations!: Location[]
  selectedAppliances: string[] = []
  selectedLocation!: Location
  newLocation = ''

  constructor(private primengConfig: PrimeNGConfig) {
    this.locations = [
      {
        locationId: 1,
        name: 'Villa in Turkey',
        appliances: ['Microwave', 'Coffee Machine'],
        provisions: ['Kitchenware', 'Bedding'],
        facilities: ['Swimming Pool', 'Garden'],
      },
      {
        locationId: 2,
        name: 'House in Wallonia',
        appliances: ['Oven', 'Fridge'],
        provisions: ['Toiletries', 'Towels'],
        facilities: ['Parking', 'Backyard'],
      },
      {
        locationId: 3,
        name: 'Apartment in New York',
        appliances: ['Dishwasher', 'Toaster'],
        provisions: ['Coffee Supplies', 'Linens'],
        facilities: ['Gym', 'Roof Deck'],
      },
      {
        locationId: 4,
        name: 'Cottage in Brazil',
        appliances: ['Blender', 'Freezer'],
        provisions: ['Cleaning Supplies', 'Pillows'],
        facilities: ['Barbecue Area', 'River Access'],
      },
      {
        locationId: 5,
        name: 'Mansion in Abu Dhabi',
        appliances: ['Washing Machine', 'Dryer'],
        provisions: ['Utensils', 'Blankets'],
        facilities: ['Spa', 'Private Beach'],
      },
    ]
    this.appliances = [
      { type: 'Microwave' },
      { type: 'Coffee machine' },
      { type: 'Oven' },
      { type: 'Fridge' },
      { type: 'Frier' },
      { type: 'Blender' },
      { type: 'Toaster' },
    ]
  }
  ngOnInit(): void {
    console.log('ngOnInit')
  }

  add() {
    console.log('added')
  }

  addLocation(string: string) {
    const lastLocationId =
      this.locations.length > 0
        ? this.locations[this.locations.length - 1].locationId
        : 0
    const nextLocationId = lastLocationId + 1
    const newLocation: Location = {
      locationId: nextLocationId,
      name: string.trim(),
      appliances: [],
      provisions: [],
      facilities: [],
    }
    this.locations.push(newLocation)
  }

  delete(locationId: number) {
    const index = this.locations.findIndex(
      location => location.locationId === locationId
    )
    if (index !== -1) {
      this.locations.splice(index, 1)
    }
  }

  onLocationSelect(event: any) {
    console.log(this.selectedAppliances)
    this.selectedAppliances = this.selectedLocation
      ? this.selectedLocation.appliances
      : []
  }
}
