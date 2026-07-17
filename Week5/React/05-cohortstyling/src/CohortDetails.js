import React from 'react';
import styles from './CohortDetails.module.css';

// Exercise 5: style a cohort card using a CSS Module.
// - the container div uses the "box" class from the module
// - the <h3> is green when the cohort status is "ongoing", otherwise blue
function CohortDetails({ cohort }) {
  const headingColor = cohort.status === 'ongoing' ? 'green' : 'blue';

  return (
    <div className={styles.box}>
      <h3 style={{ color: headingColor }}>{cohort.cohortCode}</h3>
      <dl>
        <dt>Technology</dt>
        <dd>{cohort.technology}</dd>
        <dt>Trainer</dt>
        <dd>{cohort.trainer}</dd>
        <dt>Duration</dt>
        <dd>
          {cohort.startDate} to {cohort.endDate}
        </dd>
        <dt>Status</dt>
        <dd>{cohort.status}</dd>
      </dl>
    </div>
  );
}

export default CohortDetails;
