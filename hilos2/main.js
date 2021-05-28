const canvas = document.getElementById('draw');
const color = document.getElementById('color');
const input = document.getElementById('stroke');
const image = document.getElementById('image');
const score = document.getElementById('score');
const container = document.getElementById('img_container')

const ctx = canvas.getContext("2d");
const BB = canvas.getBoundingClientRect();
var offsetX = BB.left;
var offsetY = BB.top;

var lastX, lastY;
var isDown = false;

let fromSelect;
let stroke;
let point = 0;
let height = 5;
const imgHeight = image.getClientRects()[0].height;
const colors = [
    {
        name: 'Negro',
        hex: '#000000'
    },
    {
        name: 'Rojo',
        hex: '#e63946'
    },
    {
        name: 'Verde',
        hex: '#2b9348'
    },
    {
        name: 'Café',
        hex: '#774936'
    },
    {
        name: 'Amarillo',
        hex: '#ffd60a'
    },
    {
        name: 'Azul',
        hex: '#003566'
    },
];

color.innerHTML += colors.map(c => {
    return `<option value=${c.hex}>${c.name}</option>`
})

color.addEventListener('change', e => {
    const { value } = e.target;
    fromSelect = value;
})

function onlyNumber(e) {
    var key = e.keyCode || e.which

    var keyboard = String.fromCharCode(key)

    var number = '0123456789'

    var special = "8-37-38-46"

    var keyboard_special = false

    for (let i in special) {
        if (key === special[i]) {
            keyboard_special = true
        }
    }

    if (number.indexOf(keyboard) === -1 && !keyboard_special) {
        e.preventDefault()
        return false
    }

    return true

}

function onChange(e) {
    stroke = e.target.value
}

input.addEventListener('keypress', onlyNumber)
input.addEventListener('keyup', onChange)


function drawWithMouse() {
    canvas.onmousedown = handleMousedown;
    canvas.onmousemove = handleMousemove;
    canvas.onmouseup = handleMouseup;

    function handleMousedown(e) {
        e.preventDefault();
        e.stopPropagation();
        lastX = e.clientX - offsetX;
        lastY = e.clientY - offsetY;
        isDown = true;
    }

    function handleMouseup(e) {
        e.preventDefault();
        e.stopPropagation();
        isDown = true;
    }

    function handleMousemove(e) {
        e.preventDefault();
        e.stopPropagation();

        if (!isDown) { return; }
        point++;
        height+=5;
        score.innerText = point;
        draw()

        var mouseX = e.clientX - offsetX;
        var mouseY = e.clientY - offsetY;

        ctx.beginPath();
        ctx.moveTo(lastX, lastY);
        ctx.lineTo(mouseX, mouseY);
        ctx.stroke();
        ctx.lineWidth = stroke;
        ctx.strokeStyle = fromSelect;

        lastX = mouseX;
        lastY = mouseY;
    }
}

function draw() {
    for (let i = 0; i < 50; i++) {
        let animate = document.createElement('div')
        animate.classList.add('animation')
        document.querySelector('.draw').appendChild(animate);
        document.querySelectorAll('.animation').forEach(an => {
            an.style.height = height + 'px'
        })
    }
}

function drawImg() {
    container.innerHTML +=' <div class="draw"><div class="text"><h3>Game Over</h3></div></div>'
    
    /* const top = document.querySelector('h3').getClientRects()[0].top; */
    document.querySelector('.draw').style.height = imgHeight / 2 + 'px';
    
}

drawImg();

Concurrent.Thread.create(drawWithMouse);
Concurrent.Thread.create(draw);