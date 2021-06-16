import React from 'react';
import PropTypes from 'prop-types';

function Card({ questionItem }) {

  return (
    <div className={questionItem.isAnswered == 'true' ? "is-answered d-flex flex-row justify-content-between mx-1" : "d-flex flex-row justify-content-between mx-1"}>
      <div className="d-flex flex-column p-3">
        <a href={questionItem.link}><p className="mb-1">{questionItem.title}</p></a>
        <small className="text-muted">Author: {questionItem.author}</small>
      </div>
      <div className="price pt-3 pl-3">
        <span className="mb-2">{questionItem.date}</span>
      </div>
    </div>
  );
}

Card.propTypes = {
  questionItem: PropTypes.object.isRequired
};

export default Card;