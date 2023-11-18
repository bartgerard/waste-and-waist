import { NgModule } from '@angular/core'
import { RouterModule, Routes } from '@angular/router'
import { HouseholdComponent } from './components/overview/household.component'

const routes: Routes = [
  {
    path: 'households',
    component: HouseholdComponent,
  },
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HouseholdRoutingModule {}
