import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExceptionService } from './exception.service';
import { AppRoutingModule } from '../app-routing.module';

@NgModule({
  imports: [
    CommonModule,
    AppRoutingModule
  ],
  providers: [
    ExceptionService
  ]
})
export class CoreModule { }
