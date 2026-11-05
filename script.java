/* --- PENGATURAN SLIDER & INTERAKSI --- */

// Mengambil elemen-elemen penting dari HTML
const sliderWrapper = document.getElementById('sliderWrapper');
const cards = document.querySelectorAll('.card');
const dots = document.querySelectorAll('.dot');

// Variabel untuk melacak posisi slide saat ini
let currentIndex = 0;
const totalSlides = cards.length;

/**
 * Fungsi untuk memperbarui tampilan slide berdasarkan indeks aktif
 * @param {number} index - Indeks slide yang dituju
 */
function updateSlider(index) {
    // Memastikan indeks berada dalam batas yang benar
    if (index < 0) {
        currentIndex = 0;
    } else if (index >= totalSlides) {
        currentIndex = totalSlides - 1;
    } else {
        currentIndex = index;
    }

    // Menggulung (scroll) wrapper ke posisi kartu yang sesuai
    const cardWidth = cards[0].offsetWidth + 20; // Lebar kartu ditambah gap (20px)
    sliderWrapper.scrollTo({
        left: cardWidth * currentIndex,
        behavior: 'smooth'
    });

    // Memperbarui status titik aktif (dots)
    updateDots();
}

/**
 * Fungsi untuk tombol navigasi ke depan (Next)
 */
function nextSlide() {
    if (currentIndex < totalSlides - 1) {
        updateSlider(currentIndex + 1);
    } else {
        // Kembali ke awal jika sudah di slide terakhir (opsional)
        updateSlider(0);
    }
}

/**
 * Fungsi untuk tombol navigasi ke belakang (Prev)
 */
function prevSlide() {
    if (currentIndex > 0) {
        updateSlider(currentIndex - 1);
    } else {
        // Pergi ke slide terakhir jika menekan prev di slide pertama
        updateSlider(totalSlides - 1);
    }
}

/**
 * Fungsi untuk berpindah slide langsung saat titik (dot) diklik
 * @param {number} index - Indeks target dari dot
 */
function currentSlide(index) {
    updateSlider(index);
}

/**
 * Fungsi untuk memperbarui kelas 'active' pada indikator titik
 */
function updateDots() {
    dots.forEach((dot, index) => {
        if (index === currentIndex) {
            dot.classList.add('active');
        } else {
            dot.classList.remove('active');
        }
    });
}

// Mendengarkan event scroll manual (misal digeser pakai jari/touchscreen di HP)
sliderWrapper.addEventListener('scroll', () => {
    const cardWidth = cards[0].offsetWidth + 20;
    // Menghitung indeks berdasarkan posisi scroll saat ini
    const scrollIndex = Math.round(sliderWrapper.scrollLeft / cardWidth);
    
    if (scrollIndex !== currentIndex && scrollIndex >= 0 && scrollIndex < totalSlides) {
        currentIndex = scrollIndex;
        updateDots();
    }
});
