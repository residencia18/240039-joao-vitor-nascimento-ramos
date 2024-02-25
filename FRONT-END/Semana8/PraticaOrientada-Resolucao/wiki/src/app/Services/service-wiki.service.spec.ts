import { TestBed } from '@angular/core/testing';

import { ServiceWikiService } from './service-wiki.service';

describe('ServiceWikiService', () => {
  let service: ServiceWikiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceWikiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
