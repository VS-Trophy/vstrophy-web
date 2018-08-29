import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewsItemEditorComponent } from './news-item-editor/news-item-editor.component';
import {MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatButtonModule, MatListModule, MatDividerModule, MatCardModule } from '@angular/material';
import { NewsItemEditorViewComponent } from './news-item-editor-view/news-item-editor-view.component';
import { FormsModule } from '@angular/forms';
import { NewsItemListComponent } from './news-item-list/news-item-list.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NewsFeedViewComponent } from './news-feed-view/news-feed-view.component';
import { NewsCardComponent } from './news-card/news-card.component';
import { CoreModule } from '../core/core.module';
import { NewsItemDetailViewComponent } from './news-item-detail-view/news-item-detail-view.component';


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
    //Flex Layout
    FlexLayoutModule,
    //Our Core Module
    CoreModule
  ],
  declarations: [NewsItemEditorComponent, NewsItemEditorViewComponent, NewsItemListComponent, NewsFeedViewComponent, NewsCardComponent, NewsItemDetailViewComponent]
})
export class NewsModule { }
