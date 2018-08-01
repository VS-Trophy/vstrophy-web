import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewsItemEditorComponent } from './news-item-editor/news-item-editor.component';
import {MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatButtonModule, MatListModule, MatDividerModule } from '@angular/material';
import { NewsItemEditorViewComponent } from './news-item-editor-view/news-item-editor-view.component';
import { FormsModule } from '@angular/forms';
import { NewsItemListComponent } from './news-item-list/news-item-list.component';
import { FlexLayoutModule } from '@angular/flex-layout';


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
    //Flex Layout
    FlexLayoutModule
  ],
  declarations: [NewsItemEditorComponent, NewsItemEditorViewComponent, NewsItemListComponent]
})
export class NewsModule { }