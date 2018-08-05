import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsFeedViewComponent } from './news-feed-view.component';

describe('NewsFeedViewComponent', () => {
  let component: NewsFeedViewComponent;
  let fixture: ComponentFixture<NewsFeedViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsFeedViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsFeedViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
