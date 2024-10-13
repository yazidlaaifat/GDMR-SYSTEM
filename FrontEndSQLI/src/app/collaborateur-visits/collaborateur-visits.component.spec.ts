import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CollaborateurVisitsComponent } from './collaborateur-visits.component';

describe('CollaborateurVisitsComponent', () => {
  let component: CollaborateurVisitsComponent;
  let fixture: ComponentFixture<CollaborateurVisitsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CollaborateurVisitsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CollaborateurVisitsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
