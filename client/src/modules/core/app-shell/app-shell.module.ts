import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'
import { AppShellComponent } from './app-shell.component'
import { SideNavComponent } from './components/side-nav/side-nav.component'
import { SidebarModule } from 'primeng/sidebar'
import { RouterModule } from '@angular/router'

@NgModule({
  declarations: [AppShellComponent, SideNavComponent],
  imports: [CommonModule, SidebarModule, RouterModule],
  exports: [AppShellComponent],
})
export class AppShellModule {}
