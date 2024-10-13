import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CollaborateurProfileComponent } from './collaborateur-profile.component';

describe('CollaborateurProfileComponent', () => {
  let component: CollaborateurProfileComponent;
  let fixture: ComponentFixture<CollaborateurProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CollaborateurProfileComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CollaborateurProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
