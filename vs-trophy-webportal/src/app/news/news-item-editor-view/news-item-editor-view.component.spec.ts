import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsItemEditorViewComponent } from './news-item-editor-view.component';

describe('NewsItemEditorViewComponent', () => {
  let component: NewsItemEditorViewComponent;
  let fixture: ComponentFixture<NewsItemEditorViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsItemEditorViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsItemEditorViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
