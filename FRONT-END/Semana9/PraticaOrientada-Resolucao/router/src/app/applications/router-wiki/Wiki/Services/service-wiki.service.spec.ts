import { TestBed } from '@angular/core/testing';

import { WikiService } from './service-wiki.service';

describe('ServiceWikiService', () => {
  let service: WikiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WikiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
