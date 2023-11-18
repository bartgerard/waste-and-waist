import { NgModule } from '@angular/core'
import { RouterModule, Routes } from '@angular/router'
import { OverviewComponent } from './components/overview/overview.component'

const routes: Routes = [
  {
    path: 'households',
    component: OverviewComponent,
  },
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HouseholdRoutingModule {}
