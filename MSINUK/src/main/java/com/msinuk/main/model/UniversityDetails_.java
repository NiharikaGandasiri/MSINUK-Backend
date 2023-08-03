package com.msinuk.main.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.MapAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UniversityDetails.class)
public class UniversityDetails_ {
	public static volatile MapAttribute<UniversityDetails, String, String[]> courses;
	public static volatile SingularAttribute<UniversityDetails, Long> id;
	public static volatile SingularAttribute<UniversityDetails, String> address;
	public static volatile SingularAttribute<UniversityDetails, String> contactDetails;
	public static volatile SingularAttribute<UniversityDetails, String> description;
	public static volatile SingularAttribute<UniversityDetails, String[]> images;
	public static volatile SingularAttribute<UniversityDetails, Double> rating;
	public static volatile SingularAttribute<UniversityDetails, String> universityName;
	public static volatile SingularAttribute<UniversityDetails, String> reviews;

}
