import { TestBed, inject } from '@angular/core/testing';

import { NewsItemService } from './news-item.service';

describe('NewsItemService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [NewsItemService]
    });
  });

  it('should be created', inject([NewsItemService], (service: NewsItemService) => {
    expect(service).toBeTruthy();
  }));
});
