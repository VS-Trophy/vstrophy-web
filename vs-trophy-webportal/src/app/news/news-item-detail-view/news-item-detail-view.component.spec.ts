import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsItemDetailViewComponent } from './news-item-detail-view.component';

describe('NewsItemDetailViewComponent', () => {
  let component: NewsItemDetailViewComponent;
  let fixture: ComponentFixture<NewsItemDetailViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsItemDetailViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsItemDetailViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
