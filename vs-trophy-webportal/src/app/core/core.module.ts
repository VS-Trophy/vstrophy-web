import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExceptionService } from './exception.service';
import { AppRoutingModule } from '../app-routing.module';
import { NavigationComponent } from './navigation/navigation.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { TruncateHtmlPipe } from './truncate-html.pipe';
import { FlexLayoutModule } from '@angular/flex-layout';
import { SanitizeHtmlPipe } from './sanitize-html.pipe';

@NgModule({
  imports: [
    CommonModule,
    AppRoutingModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    //Flex Layout
    FlexLayoutModule
  ],
  providers: [
    ExceptionService
  ],
  declarations: [NavigationComponent, TruncateHtmlPipe, SanitizeHtmlPipe],
  exports: [NavigationComponent,TruncateHtmlPipe,SanitizeHtmlPipe]
})
export class CoreModule { }
