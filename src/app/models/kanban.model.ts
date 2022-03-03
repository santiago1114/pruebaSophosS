export class Kanban{

    constructor(
        public id: number, 
        public name: string,
        public todo: string[],
        public inprogress: string[],
        public done: string[]){}

}