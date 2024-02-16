let divs = document.getElementsByTagName("div")
const val = Array.prototype.map.call(divs,({innerHTML}) => innerHTML)
console.log(val)

const dobrar=(e)=>e*2

const converterInt=(e)=>parseInt(e);

let num =['1','2','3','4','5','6'].map(dobrar)

console.log(num)