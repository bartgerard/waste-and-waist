import { Component, Input } from '@angular/core'
import { HouseHold } from '@/types/household'

@Component({
  selector: 'app-selected-house-hold',
  templateUrl: './selected-house-hold.component.html',
  styleUrls: ['./selected-house-hold.component.scss'],
})
export class SelectedHouseHoldComponent {
  @Input() houseHold?: null | HouseHold = null
}
