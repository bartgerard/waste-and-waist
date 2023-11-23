import { ComponentFixture, TestBed } from '@angular/core/testing'

import { SelectedHouseHoldComponent } from './selected-house-hold.component'

describe('HouseHoldItemComponent', () => {
  let component: SelectedHouseHoldComponent
  let fixture: ComponentFixture<SelectedHouseHoldComponent>

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SelectedHouseHoldComponent],
    })
    fixture = TestBed.createComponent(SelectedHouseHoldComponent)
    component = fixture.componentInstance
    fixture.detectChanges()
  })

  it('should create', () => {
    expect(component).toBeTruthy()
  })
})
