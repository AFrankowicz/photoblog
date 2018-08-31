import { Component, OnInit, Input } from '@angular/core';

@Component({
    selector: 'app-board',
    templateUrl: './board.component.html'
})
export class BoardComponent {

    @Input()
    posts: any;

    constructor() {}

    ngOnInit() {}
}
