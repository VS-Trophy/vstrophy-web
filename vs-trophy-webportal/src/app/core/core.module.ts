import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExceptionService } from './exception.service';
import { NavigationComponent } from './navigation/navigation.component';
import { AppRoutingModule } from '../app-routing.module';

@NgModule({
  imports: [
    CommonModule,
    AppRoutingModule
  ],
  providers: [
    ExceptionService
  ],
  declarations: [NavigationComponent],
  exports: [NavigationComponent]
})
export class CoreModule { }
