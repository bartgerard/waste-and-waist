import { Component, OnInit } from '@angular/core'
import { format } from 'date-fns'
import { finalize, Observable } from 'rxjs'
import { HouseholdService } from '@/modules/household/services/household.service'
import { UserService } from '@/modules/user/services/user.service'
import { FormControl, FormGroup, Validators } from '@angular/forms'
import { HouseHold } from '@/types/household'

@Component({
  selector: 'app-household',
  templateUrl: './household.component.html',
  styleUrls: ['./household.component.scss'],
})
export class HouseholdComponent implements OnInit {
  formGroup = new FormGroup({
    houseHoldName: new FormControl('', Validators.required),
    memberName: new FormControl('', Validators.required),
    birthDate: new FormControl(new Date(), Validators.required),
  })
  householdFormGroup = new FormGroup({
    selectedHouseHold: new FormControl<HouseHold | null>(null),
  })
  loading = false

  households$?: Observable<{
    houseHolds: HouseHold[]
  }>

  constructor(
    private householdService: HouseholdService,
    private userService: UserService
  ) {}
  ngOnInit(): void {
    this.getHouseholds()
  }

  getHouseholds(): void {
    if (!this.userService.user?.userId) return
    this.loading = true

    this.households$ = this.householdService
      .get(this.userService.user?.userId)
      .pipe(
        finalize(() => {
          this.loading = false
        })
      )
  }

  addHousehold() {
    if (this.formGroup.invalid || !this.userService.user?.userId) return

    const data = {
      ...this.formGroup.value,
      birthDate: format(
        this.formGroup.controls.birthDate.value as Date,
        'yyyy-MM-dd'
      ),
      userId: this.userService.user.userId,
    }

    this.householdService
      .add(
        data as {
          memberName: string
          userId: string
          birthDate: string
          houseHoldName: string
        }
      )
      .subscribe(() => {
        this.getHouseholds()
      })
  }
}
