import { Component, OnInit } from '@angular/core'
import { FormBuilder, FormGroup, Validators } from '@angular/forms'
import { Router } from '@angular/router'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  formGroup!: FormGroup
  fmBuilder!: FormBuilder

  constructor(
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.fmBuilder = formBuilder
  }

  submitted = false

  ngOnInit() {
    this.formGroup = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    })
  }

  login() {
    this.router.navigate(['/households'])
  }

  onSubmit() {
    console.log('submit')
  }
}
