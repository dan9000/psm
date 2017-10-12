/*
 * Copyright 2012-2013 TopCoder, Inc.
 *
 * This code was developed under U.S. government contract NNH10CD71C.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gov.medicaid.entities;

import gov.medicaid.domain.model.ApplicantType;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * Represents a provider type.
 *
 * @author argolite, TCSASSEMBLER
 * @version 1.0
 */
@javax.persistence.Entity
@Table(name = "provider_types")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "ProviderType with AgreementDocuments",
                attributeNodes = {@NamedAttributeNode("agreementDocuments")}
        ),
        @NamedEntityGraph(
                name = "ProviderType with LicenseTypes",
                attributeNodes = {@NamedAttributeNode("licenseTypes")}
        )
})
public class ProviderType extends LookupEntity {

    /**
     * Individual or organization.
     */
    @Column(
            name = "applicant_type",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private ApplicantType applicantType = ApplicantType.INDIVIDUAL;

    /**
     * Flag to delete or not.
     */
    @Transient
    private boolean canDelete;

    @ManyToMany
    @JoinTable(
            name = "provider_type_agreement_documents",
            joinColumns = @JoinColumn(
                    name = "provider_type_code",
                    referencedColumnName = "code"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "agreement_document_id",
                    referencedColumnName = "agreement_document_id"
            )
    )
    private List<AgreementDocument> agreementDocuments;

    @ManyToMany
    @JoinTable(
            name = "provider_type_license_types",
            joinColumns = @JoinColumn(
                    name = "provider_type_code",
                    referencedColumnName = "code"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "license_type_code",
                    referencedColumnName = "code"
            )
    )
    private List<LicenseType> licenseTypes;

   /**
     * Gets the value of the field <code>applicantType</code>.
     * @return the applicantType
     */
    public ApplicantType getApplicantType() {
        return applicantType;
    }

    /**
     * Sets the value of the field <code>applicantType</code>.
     * @param applicantType the applicantType to set
     */
    public void setApplicantType(ApplicantType applicantType) {
        this.applicantType = applicantType;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    public List<AgreementDocument> getAgreementDocuments() {
        return agreementDocuments;
    }

    public void setAgreementDocuments(List<AgreementDocument> agreementDocuments) {
        this.agreementDocuments = agreementDocuments;
    }

    public List<LicenseType> getLicenseTypes() {
        return licenseTypes;
    }

    public void setLicenseTypes(List<LicenseType> licenseTypes) {
        this.licenseTypes = licenseTypes;
    }
}
