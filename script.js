let hourValue = 0;
let minuteValue = 0;
let secondValue = 0;
let milisecondValue = 0;
let flag = true;
let Timer;

let msec = document.querySelector(".miliseconds");
let sec = document.querySelector(".seconds");
let min = document.querySelector(".minutes");
let hr = document.querySelector(".hours");

let startButton = document.querySelector("#start-button");
let resetButton = document.querySelector("#reset-button");
let resetIcon = document.querySelector('#reset-icon');

const toggleTimer = () => {
  if (flag) {
    startButton.innerHTML = `<i class="fa-solid fa-pause"></i>`;
    startButton.style.backgroundColor = "#bf0603";
    startTimer();
    flag = false;
  } else {
    startButton.innerHTML = `<i class="fa-solid fa-play"></i>`;
    startButton.style.backgroundColor = "#196e19";
    stopTimer();
    flag = true;
  }
};

startButton.addEventListener('click', toggleTimer);

const startTimer = () => {
  Timer = setInterval(() => {
    milisecondValue++;

    if(milisecondValue === 100) {
        secondValue++;
        sec.textContent = `${secondValue.toString().padStart(2, '0')}`;
        milisecondValue = 0;
    }

    if(secondValue === 60) {
        minuteValue++;
        min.textContent = `${minuteValue.toString().padStart(2, '0')}`;
        secondValue = 0;
        sec.textContent = `${secondValue.toString().padStart(2, '0')}`;
    }

    if(minuteValue === 60) {
        hourValue++;
        hr.textContent = `${hourValue.toString().padStart(2, '0')}`;
        minuteValue = 0;
        min.textContent = `${minuteValue.toString().padStart(2, '0')}`;
    }
    
    msec.textContent = `${milisecondValue.toString().padStart(2, '0')}`;
  }, 10);
};

const stopTimer = () => {
    clearInterval(Timer);
}; 

const resetTimer = () => {
    clearInterval(Timer);
    flag = true;
    startButton.innerHTML = `<i class="fa-solid fa-play"></i>`;
    startButton.style.backgroundColor = "#196e19";
    hourValue = 0;
    minuteValue = 0;
    secondValue = 0;
    milisecondValue = 0;
    hr.textContent = `${hourValue.toString().padStart(2, '0')}`;
    min.textContent = `${minuteValue.toString().padStart(2, '0')}`;
    sec.textContent = `${secondValue.toString().padStart(2, '0')}`;
    msec.textContent = `${milisecondValue.toString().padStart(2, '0')}`;

    resetIcon.classList.add('rotate');

    setTimeout(() => {
        resetIcon.classList.remove('rotate');
    }, 500);
};
