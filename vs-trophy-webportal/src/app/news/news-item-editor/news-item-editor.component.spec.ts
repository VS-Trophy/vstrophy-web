import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsItemEditorComponent } from './news-item-editor.component';

describe('NewsItemEditorComponent', () => {
  let component: NewsItemEditorComponent;
  let fixture: ComponentFixture<NewsItemEditorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsItemEditorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsItemEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
