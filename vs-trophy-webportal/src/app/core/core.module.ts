import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExceptionService } from './exception.service';

@NgModule({
  imports: [
    CommonModule
  ],
  providers: [
    ExceptionService
  ],
  declarations: []
})
export class CoreModule { }
