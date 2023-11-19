import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'
import { ButtonModule } from 'primeng/button'
import { ReactiveFormsModule } from '@angular/forms'

@NgModule({
  declarations: [],
  imports: [CommonModule, ButtonModule, ReactiveFormsModule],
  exports: [CommonModule, ButtonModule, ReactiveFormsModule],
})
export class SharedModule {}
