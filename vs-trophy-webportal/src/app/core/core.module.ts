import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExceptionService } from './exception.service';
import { AppRoutingModule } from '../app-routing.module';
import { NavigationComponent } from './navigation/navigation.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule, MatButtonModule, MatSidenavModule, MatIconModule, MatListModule } from '@angular/material';
import { TruncateHtmlPipe } from './truncate-html.pipe';

@NgModule({
  imports: [
    CommonModule,
    AppRoutingModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule
  ],
  providers: [
    ExceptionService
  ],
  declarations: [NavigationComponent, TruncateHtmlPipe],
  exports: [NavigationComponent,TruncateHtmlPipe]
})
export class CoreModule { }
