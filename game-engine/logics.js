//Cards definition

const ranks = [3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14];
const suites = ['s', 'h', 'c', 'd'];


const get_pool = function() {

    pool = []
    for (var i = 0; i < ranks.length; i++) {
        for (var j = 0; j < suites.length; j++) {
            pool.push([ranks[i], suites[j]])
        }
    }

    return pool;
}

/**
 *	Function to shuffle cards and return 6 arrays of cards for each player
 */
function shuffle_cards() {

    total_pool = get_pool()
    to_return = new Array();
    for (var i = 0; i < 6; i++) {
        var temp = []
        for (var j = 0; j < 8; j++) {
            random_index = Math.floor(Math.random() * total_pool.length)
            elem = total_pool.splice(random_index, 1)
            temp.push(elem[0])
        }
        to_return.push(temp)
    }
    return to_return;
}

function calc_point_per_hand(cards) {
    total = 0;
    for (var i = 0; i < cards.length; i++) {
        card = cards[i]
        if (card[0] == 12 && card[1] == 's') {
            total += 40
        } else if (card[0] == 5) {
            total += 5
        } else if (card[0] >= 10 && card[0] != 14) {
            total += 10
        } else if (card[0] == 14) {
            total += 15
        }
    }
    return total;
}


function determine_hand(trump, round_cards) {

    // round_cards has [{user_id: [rank, suite]}]
    console.log(round_cards)
    ruler_suite = Object.values(round_cards[0])[0][1]


    round_cards.sort((x, y) => {
        card1 = Object.values(x)[0]
        card2 = Object.values(y)[0]

        if (card1[1] == trump && card2[1] == trump) {
            return card1[0] > card2[0] ? -1 : 1;
        } else if (card1[1] == ruler_suite && card2[1] == trump) {
            return 1;
        } else if (card1[1] == trump && card2[1] == ruler_suite) {
            return -1;
        } else if (card1[1] == ruler_suite && card2[1] == ruler_suite) {
            return card1[0] > card2[0] ? -1 : 1;
        } else if (card1[1] == trump && card2[1] != ruler_suite) {
            return -1;
        } else if (card1[1] != ruler_suite && card2[1] == trump) {
            return 1;
        } else {
            return card1[0] > card2[0] ? -1 : 1;
        }
    })

    console.log(round_cards)


    return {
        'winner': round_cards[0],
        'points': calc_point_per_hand(round_cards.map(info => Object.values(info)[0]))
    }

}


// console.log(determine_hand('d', [{23123: [14,'s']}, {123: [3,'h']}, {23981: [6,'d']}, {23888: [7,'s']}, {11442: [14,'d']}, {1234: [11,'c']}]))

exports.shuffle_cards = shuffle_cards;
exports.determine_hand = determine_hand;