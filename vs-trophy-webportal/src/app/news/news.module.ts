import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewsItemEditorComponent } from './news-item-editor/news-item-editor.component';
import {MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { NewsItemEditorViewComponent } from './news-item-editor-view/news-item-editor-view.component';
import { FormsModule } from '@angular/forms';
import { NewsItemListComponent } from './news-item-list/news-item-list.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NewsFeedViewComponent } from './news-feed-view/news-feed-view.component';
import { NewsCardComponent } from './news-card/news-card.component';
import { CoreModule } from '../core/core.module';
import { NewsItemDetailViewComponent } from './news-item-detail-view/news-item-detail-view.component';
import { AngularEditorModule } from '@kolkov/angular-editor';
import {MatIconModule} from '@angular/material/icon';


@NgModule({
  imports: [
    FormsModule,
    CommonModule,
    //Angular Material
    MatInputModule,
    MatDatepickerModule,
    MatButtonModule,
    MatListModule,
    MatDividerModule,
    MatCardModule,
    MatIconModule,
    //Flex Layout
    FlexLayoutModule,
    //The Editor
    AngularEditorModule,
    //Our Core Module
    CoreModule
  ],
  declarations: [NewsItemEditorComponent, NewsItemEditorViewComponent, NewsItemListComponent, NewsFeedViewComponent, NewsCardComponent, NewsItemDetailViewComponent]
})
export class NewsModule { }
