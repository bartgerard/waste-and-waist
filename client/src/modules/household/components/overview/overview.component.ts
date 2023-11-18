import { Component, OnInit } from '@angular/core'
import { PrimeNGConfig } from 'primeng/api'

@Component({
  selector: 'app-root',
  templateUrl: './overview.component.html',
})
export class OverviewComponent implements OnInit {
  title = 'client'

  constructor(private primengConfig: PrimeNGConfig) {}

  ngOnInit(): void {
    }
  }